class School
  def initialize
    @database = Hash.new
  end

  def db
    database
  end

  def add(name, grade)
    names = database[grade] || []
    names << name
    database.merge!({grade => names})
  end

  def grade(key)
    database.fetch(key, [])
  end

  def sort
    hash = {}
    alphabetical_sort(database).map do |hsh|
      hash.merge!({ hsh[0] => alphabetical_sort(hsh[1]) })
    end.first
  end

  private
  attr_accessor :database

  def alphabetical_sort(values)
    if values.is_a?(Hash)
      values.sort_by { |k, v| k }
    else
      values.sort
    end
  end
end
