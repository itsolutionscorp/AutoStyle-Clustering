class School
  attr_accessor :_db

  def initialize
    @_db = []
  end

  def add(name, grade)
    _db[grade] ||= []
    _db[grade] << name
  end

  def db
    index = 0
    _db.each_with_object({}) do |entries,hash|
      values = entries
      if values
        if block_given?
          values = yield values
        end
        hash[index] = values
      end
      index += 1
    end
  end

  def grade(number)
    _db.fetch(number, []).dup
  end

  def sort
    db{|values| values.sort }
  end
end
