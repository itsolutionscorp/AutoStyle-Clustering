class School

  def initialize
    @database = {}
  end

  def db
    database.clone.freeze
  end

  def add(student, grade)
    database.fetch(grade){|g| database[g] = [] } << student
  end

  def grade(grade)
    database.fetch(grade, [])
  end

  def sort
    db.keys.sort.each_with_object({}){ |k,h| h[k] = db[k].sort }
  end

  private

  def database
    @database
  end
end
