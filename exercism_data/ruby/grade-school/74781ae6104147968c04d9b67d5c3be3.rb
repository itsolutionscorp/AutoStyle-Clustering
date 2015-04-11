class School
  def initialize
    @roll = empty_school_roll
  end

  def add(student_name, grade_num)
    roll[grade_num] << student_name
  end

  def grade(grade_num)
    roll[grade_num].sort
  end

  def to_hash
    all_grades.each_with_object({}) do |grade_num, hash|
      hash[grade_num] = grade(grade_num)
    end
  end

  private

  attr_reader :roll

  def empty_school_roll
    Hash.new do |roll, grade| 
      roll[grade] = []
    end
  end

  def all_grades
    roll.keys.sort
  end
end
