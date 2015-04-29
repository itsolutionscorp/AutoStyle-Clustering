class School

  def initialize
    @db = Hash.new { |grade, student| grade[student] = [] }
  end

  def db
    @db 
  end

  def add(student, grade)
    @db[grade] << student
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    sorted_grades.each_with_object({}) do |grade_num, sorted|
      sorted[grade_num] = grade(grade_num).sort
    end
  end

  def sorted_grades
    @db.keys.sort
  end
end
