class School

  def initialize
    @students = {}
  end

  def to_hash
    @students
  end

  def grade(grade)
    to_hash[grade] || []
  end

  def add(name, grade_level)
    @students[grade_level] ||= []
    @students[grade_level] << name
    
    @students[grade_level].sort!
    @students = Hash[*@students.sort.each_slice(3)]
  end
end
