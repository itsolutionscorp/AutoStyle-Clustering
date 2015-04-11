class School
  def initialize
    @student_db = Hash.new { |hash, key| hash[key] = [] }
  end

  def to_hash
    sorted_student_db = {}
    sorted_grade_nums = student_db.keys.sort

    sorted_grade_nums.each do |grade_num|
      sorted_student_db[grade_num] = grade(grade_num)
    end

    sorted_student_db
  end

  def add(name, grade)
    student_db[grade] << name
  end

  def grade(num)
    student_db[num].sort
  end

  private

  attr_accessor :student_db
end
