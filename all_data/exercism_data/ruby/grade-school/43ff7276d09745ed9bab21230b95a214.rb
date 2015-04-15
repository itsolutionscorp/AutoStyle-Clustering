class School

  def initialize
    @db = Hash.new {|hsh,key| hsh[key] = []}
  end

  def db
    @db.dup
  end

  def add(name, grade)
    name, grade = name.to_s, grade.to_i
    @db[grade] << name unless @db[grade].include?(name)
  end

  def grade(grade)
    db[grade] || []
  end

  def sort
    Hash[db.sort_by {|grade,students| students.sort!; grade}]
  end

end
