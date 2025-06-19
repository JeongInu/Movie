import React, { useState } from 'react';
import { Button } from "@/components/ui/button";
import { uploadFile } from '@/api/fileApi';

const FileUpload = () => {
  const [file, setFile] = useState<File | null>(null);
  const [message, setMessage] = useState<string>('');
  const [isUploading, setIsUploading] = useState<boolean>(false);

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files.length > 0) {
      setFile(event.target.files[0]);
      setMessage('');
    }
  };

  const handleUpload = async () => {
    if (!file) {
      setMessage('파일을 선택해주세요.');
      return;
    }

    try {
      setIsUploading(true);
      setMessage('파일 업로드 중');

      const response = await uploadFile(file);

      setMessage('업로드 완료!');
      console.log('서버 응답 : ', response);

    } catch (error) {
      console.error(error);
      setMessage("오류 발생!");
    } finally {
      setIsUploading(false);
    }

  };

  return (
    <div className="flex flex-col items-center justify-center p-8 bg-gray-100 rounded-lg shadow-lg w-full max-w-sm mx-auto">
      <h1 className="text-2xl font-semibold text-gray-800 mb-6">파일 업로드</h1>

      <div className="mb-4 w-full">
        <input
          type="file"
          onChange={handleFileChange}
          className="file:mr-4 file:border file:border-gray-300 file:px-6 file:py-2 file:rounded-md file:bg-gray-200 file:text-gray-700 hover:file:bg-gray-300 focus:outline-none"
        />
      </div>

      <Button
        onClick={handleUpload}
        className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition disabled:opacity-50"
        disabled={isUploading}
      >
        업로드
      </Button>

      {message && <p className="mt-4 text-lg font-medium text-gray-700">{message}</p>}
    </div>
  );

};

export default FileUpload;