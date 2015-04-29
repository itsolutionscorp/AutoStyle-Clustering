class School
  def initialize
    @students = {} 
  end
  
  def to_hash
    sorted_students = {}
    @students.keys.sort.each { |key| sorted_students[key] = @students[key] }
    sorted_students
  end

  def add name, grade
    @students[grade] ? @students[grade].push(name) : @students[grade] = [name]
    @students[grade].sort!
    @students.keys.sort!
  end

  def grade grade
    @students[grade] || []
  end
end
