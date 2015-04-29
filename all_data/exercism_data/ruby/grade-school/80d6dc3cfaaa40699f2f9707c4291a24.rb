class School

  def initialize
    @db = Hash.new { |db, grade| db[grade] = []}
  end

  def add(student, grade)
    students = @db[grade] || []
    new_grade = students.empty?
    students.include?(student) ? false : @db[grade] = students.push(student).sort
    new_grade ? @db = Hash[@db.to_a.sort] : false
  end

  def grade(level)
    @db[level]
  end

  def to_hash
    @db
  end

end
