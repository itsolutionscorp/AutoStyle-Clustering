class School
  def initialize
    @db = Hash.new {|hash, key| hash[key] = Array.new }
  end

  def db
    @db
  end

  def add(student, grade)
    @db[grade].push student
  end
  
  def grade(grade)
    @db[grade]
  end
  
  def sort
    sorted = Hash.new
    @db.keys.sort.each do |key|
      sorted[key] = @db[key].sort
    end
    sorted
  end
end