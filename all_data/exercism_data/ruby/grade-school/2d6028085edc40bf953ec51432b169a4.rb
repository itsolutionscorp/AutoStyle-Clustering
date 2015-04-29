class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(student, grade)
    db.has_key?(grade) ? db[grade] = db[grade].push(student) : db[grade] = [student]
  end

  def grade(grade)
    db.has_key?(grade) ? @db[grade] : []
  end

  def sort
    new_db = {}
    @db.keys.sort.each do |key|
      new_db[key] = @db[key].sort
    end
    @db = new_db
    p @db
  end
end
