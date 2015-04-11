class School
  def initialize
    @students_by_grade = Hash.new {|hash, key| hash[key] = [] }
  end

  def add(student, grade)
    @students_by_grade[grade] << student
  end

  def to_hash
    grades.sort.each_with_object({}) do |grade, hash|
      hash[grade] = grade(grade)
    end
  end

  def grade(number)
    @students_by_grade[number].sort
  end

  private

  def grades
    @students_by_grade.keys
  end
end
