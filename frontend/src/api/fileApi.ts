import axiosInstance from "./axiosInstance";

export const uploadFile = async (file: File) => {
  const formData = new FormData();
  formData.append('file', file);
  try {
    const response = await axiosInstance.post('/uploadAxios', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
}