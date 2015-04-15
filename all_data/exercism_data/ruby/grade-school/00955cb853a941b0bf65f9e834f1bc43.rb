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
    database.each_value(&:sort!)
    Hash[database.sort]
  end

  private

  def database
    @database
  end
end
