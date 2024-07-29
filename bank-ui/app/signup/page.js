'use client';
import React, { useState } from 'react';
import warimage from '@/public/icons/warning.svg';
import eyeimage from '@/public/icons/eye.svg';
import eyeslash from '@/public/icons/eye-slash.svg';
import Submitbutton from '@/components/submitbutton';
import Image from 'next/image';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import HomeNavBar from '@/components/HomeNavBar';
const SignUp = () => {
    const router=useRouter();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');

    const [confirmPassword, setConfirmPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const [showPassword1, setShowPassword1] = useState(false);
    const [isValidEmail, setValidEmail] = useState(true);
    const [validName, setValidName] = useState(true);

    const [matchPassword, setMatchPassword] = useState(true);
    const [messageEmail, setMessageEmail] = useState("Enter a valid email address");
    const [isLoading, setIsLoading] = useState(false);

    const handleEmailChange = (e) => {
        setMessageEmail("Enter a valid email address");
        setEmail(e.target.value);
        setValidEmail(true);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
        setMatchPassword(true);
    };

    const handleConfirmPasswordChange = (e) => {
        setConfirmPassword(e.target.value);
        setMatchPassword(true);
    };

    const handleSignUp = async (e) => {
        e.preventDefault();
        checkEmail();
        checkPassword();
        if(name.length==0)
        {
            setValidName(false);
        }
        if (validName && isValidEmail && matchPassword && password.length > 0) {
            setIsLoading(true);
            try {
                
                const response = await fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}/auth/register`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({name, email, password,role:"USER" }),
                });

                console.log(response);
                if (response.ok) {
                    alert("Signup successful");
                    const data=await response.json();
                    const token=data.response;
                    console.log(token);
                    router.push('/login');
                } else {
                    const data = await response.json();
                    if (data.response === 'User already exists') {
                        setMessageEmail(data.response);
                        setValidEmail(false);
                    } else {
                        console.error('Signup failed:', data.response);
                    }
                }
            } catch (error) {
                console.error('Error:', error);
            } finally {
                setIsLoading(false);
            }
        }
    };

    const checkEmail = () => {
    // if (email.length > 0) {
            const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
            setValidEmail(emailRegex.test(email));
      
    };

    const checkPassword = () => {
        setMatchPassword(password.length>0 && password === confirmPassword);
    };

    const togglePasswordVisibility = () => {
        setShowPassword(prev => !prev);
    };

    const togglePasswordVisibility1 = () => {
        setShowPassword1(prev => !prev);
    };

    return (
        <div className=' w-screen flex flex-col gap-2'>
            <HomeNavBar/>
            <div className="min-h-screen custom flex justify-center items-center text-sm">
                <div className="fixwidth bg-white relative bg-opacity-20 backdrop-filter backdrop-blur-[200px] border border-gray-300 h-[80%] pt-7 w-[40%] p-10 rounded-md border-1 border-solid border-yellow-500 shadow-md
                max-md:p-8 max-md:w-80 max-md:pt-8 max-md:pb-8 font-medium text-base">
                    <div className="text-3xl font-bold leading-9 text-center">
                        Create account
                    </div>
                    <div className="text-black font-open-sans text-xs font-normal leading-6 text-center">
                        Already have an account? <Link href="/login" className="underline text-yellow-500">Log In</Link>
                    </div>
                    <div className="mb-3 mt-7 font-poppins">
                        <div className="mb-2 flex justify-between">
                            <label className="text-black block">Name*</label>
                            {!validName && (
                                <div className="flex">
                                    <Image src={warimage} width={20} height={20} alt='warning' className='text-white'></Image>
                                    <p className='pl-1 text-red-700'>Name cant be empty</p>
                                </div>
                            )}
                        </div>
                        <input
                            type="text"
                            placeholder='john'
                            value={name}
                            onChange={(e)=>{setName(e.target.value); setValidName(true)}}
                            required
                            className="border border-solid border-yellow-500 w-full p-2 rounded-md text-sm font-normal"
                        />
                    </div>
                    <div className="mb-3 mt-7 font-poppins">
                        <div className="mb-2 flex justify-between">
                            <label className="text-black block">E-mail</label>
                            {!isValidEmail && (
                                <div className="flex">
                                    <Image src={warimage} width={20} height={20} alt='warning' className='text-white'></Image>
                                    <p className='pl-1 text-red-700'>{messageEmail}</p>
                                </div>
                            )}
                        </div>
                        <input
                            type="email"
                            placeholder='name@email.com'
                            value={email}
                            onBlur={checkEmail}
                            onChange={handleEmailChange}
                            className="border border-solid border-yellow-500 w-full p-2 rounded-md text-sm font-normal"
                        />
                    </div>
                    <div className="mb-4 mt-4 font-poppins">
                        <label className="mb-2 text-black block">Password:</label>
                        <div className="relative">
                            <input
                                type={showPassword ? 'text' : 'password'}
                                value={password}
                                placeholder='Set your password'
                                onChange={handlePasswordChange}
                                className="border border-solid border-yellow-500 w-full p-2 pr-10 rounded-md text-sm font-normal"
                            />
                            <Image
                                src={showPassword ? eyeslash : eyeimage}
                                width={18}
                                height={28}
                                alt="Show Password"
                                className={`absolute top-1/2 transform -translate-y-1/2 right-2 cursor-pointer ${password.length >= 1 ? 'block' : 'hidden'}`}
                                onClick={togglePasswordVisibility}
                            />
                        </div>
                    </div>
                    <div className="mb-4 mt-4 font-poppins">
                        <div className="mb-2 flex justify-between">
                            <label className="text-black">Confirm Password:</label>
                            {!matchPassword && (
                                <div className="flex justify-end text-right">
                                    <Image src={warimage} width={20} height={20} alt='warning'></Image>
                                    <p className='ml-1 text-red-700'>Password doesnt match.f</p>
                                </div>
                            )}
                        </div>
                        <div className="relative">
                            <input
                                type={showPassword1 ? 'text' : 'password'}
                                onBlur={checkPassword}
                                value={confirmPassword}
                                placeholder='Confirm your password'
                                onChange={handleConfirmPasswordChange}
                                className="border border-solid border-yellow-500 w-full p-2 rounded-md pr-10 text-sm font-normal"
                            />
                            <Image
                                src={showPassword1 ? eyeslash : eyeimage}
                                width={18}
                                height={28}
                                alt="Show Password"
                                className={`absolute top-1/2 transform -translate-y-1/2 right-2 cursor-pointer ${confirmPassword.length >= 1 ? 'block' : 'hidden'}`}
                                onClick={togglePasswordVisibility1}
                            />
                        </div>
                    </div>
                    <Submitbutton message='Sign up' handleSignIn={handleSignUp} />
                </div>
            </div>
           
           
        </div>
    );
};

export default SignUp;
