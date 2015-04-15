class School

  attr_reader :grades

  def initialize
    @grades = Hash.new {|h,k| h[k] = [] }
  end

  def add(name, grade_number)
    grade(grade_number) {|students| students << name }
  end

  def grade(grade_number)
    grades[grade_number].tap do |students|
      if block_given?
        yield students
        students.sort!
      end
    end
  end

  def to_hash
    Hash[grades.sort]
  end


end
