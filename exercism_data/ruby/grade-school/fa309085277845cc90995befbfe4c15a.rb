class School

  def initialize
    @database = {}
  end

  def db
    db = database.clone
    db.each do |grade,students|
      grade.freeze
      students.freeze
    end
    return db.freeze
  end

  def add(student, grade)
    database.fetch(grade){|g| database[g] = [] } << student
  end

  def grade(grade)
    sort.fetch(grade, [])
  end

  def sort
    db.sort.each_with_object({}){ |(grade, students), sorted_database| sorted_database[grade] = students.sort }
  end

  def to_hash
    sort
  end

  private

  def database
    @database
  end
end
