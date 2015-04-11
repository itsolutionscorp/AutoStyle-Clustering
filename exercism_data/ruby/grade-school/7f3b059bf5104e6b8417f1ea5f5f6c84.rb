class Student < Struct.new(:name, :grade); end

class School
  def students
    @students ||= []
  end

  def add(name, grade)
    students << Student.new(name, grade)
  end

  def to_hash
    hash = students.group_by(&:grade)
    hash.each do |grade, students|
      hash[grade] = students.map(&:name).sort
    end
    hash
  end

  def grade(grade)
    students.find_all do |student|
      student.grade == grade
    end
    .map(&:name).sort
  end
end
