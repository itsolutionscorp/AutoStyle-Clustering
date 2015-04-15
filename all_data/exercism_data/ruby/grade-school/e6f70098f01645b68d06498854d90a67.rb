class School

  def initialize
    @grades = default_db
  end

  def add name, grade
    grades[grade] << name
  end

  def grade grade
    grades[grade]
  end

  def sort
    sorted_hash = Hash[grades.sort_by {|grade,students| grade}]
    sorted_hash.each do |grade, students|
      sorted_hash[grade] = students.dup.sort
    end
    sorted_hash
  end

  def db
    Hash[grades.to_a]
  end

  private

  def grades
    @grades
  end

  def default_db
    Hash.new { |h,k| h[k] = [] }
  end
end
