class School
  def initialize
    @students = Hash.new [] 
  end
  
  def to_hash
    sorted_students = {}
    @students.keys.sort.each { |key| sorted_students[key] = @students[key] }
    @students = sorted_students
  end

  def add name, grade
    @students[grade] += [name]
    @students[grade].sort!
  end

  def grade grade
    @students[grade] || []
  end
end
