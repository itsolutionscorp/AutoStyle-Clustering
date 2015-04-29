class School
  def initialize
    @database = Hash.new
  end

  def db
    @database
  end

  def add(name, grade)
    names = @database[grade] || []
    names << name
    @database.merge!({grade => names})
  end

  def grade(key)
    @database.fetch(key, [])
  end

  def sort
    hash = {}
    @database.sort_by { |k, v| k }.map { |hsh| hash.merge!({ hsh[0] => hsh[1].sort }) }.first
  end
end
