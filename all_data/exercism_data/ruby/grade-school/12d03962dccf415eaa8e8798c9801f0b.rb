class School
  def initialize
    @school = Hash.new { |h,k| h[k] = [] }
  end

  def to_hash
    sorted_school = Hash.new { |h, k| h[k] = [] }

    @school.sort.to_h.each do |grade, students|
      sorted_school[grade] = students.sort
    end

    sorted_school
  end

  def add(student, class_num)
    @school[class_num] << student
  end

  def grade(class_num)
    @school[class_num].sort
  end
end
