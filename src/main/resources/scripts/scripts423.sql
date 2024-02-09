SELECT Student.name,Student.age,Faculty.name FROM Student
LEFT JOIN Faculty ON Student.faculty_id = Faculty.id;
SELECT Student.name FROM Avatar
INNER JOIN Student ON Avatar.student_id = student.id;
