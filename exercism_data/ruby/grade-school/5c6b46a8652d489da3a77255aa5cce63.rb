class School

  def initialize
    @database = {}
  end

  def db
    database.freeze
  end

  def add(student, grade)
    database.fetch(grade){|g| database[g] = [] } << student
  end

  def grade(grade)
    database.fetch(grade, [])
  end

  def sort
    nh = {}
    db.keys.sort.each{ |k| nh[k] = db[k].sort }
    return nh
  end

  private

  def database
    @database
  end
end
