class School
  def initialize
    @grades = Hash.new do |hash, key|
      hash[key] = []
    end
  end

  def add student, grade
    @grades[grade] << student
  end

  def grade grade
    @grades[grade].sort!
  end

  def to_hash
    @grades.each {|grade, students| students.sort!}
    @grades = Hash[@grades.sort]
  end
end
