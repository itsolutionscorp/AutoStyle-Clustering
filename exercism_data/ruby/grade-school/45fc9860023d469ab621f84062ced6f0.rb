class School

  def initialize
    @database = ListDatabase.new
  end

  def db
    database.all_data
  end

  def add(student, grade)
    database.add(grade, student)
  end

  def grade(n)
    database.get(n)
  end

  def sort
    ordered_grades.reduce({}) do |sorted, grade|
      sorted[grade] = grade(grade).sort
      sorted
    end
  end

  private

  attr_reader :database

  def ordered_grades
    database.ids.sort
  end

  class ListDatabase
    attr_reader :store

    def initialize
      @store = Hash.new([])
    end

    def add(key, value)
      if store.has_key?(key)
        store[key] << value
      else
        store[key] = [value]
      end
    end

    def get(key)
      store[key]
    end

    def ids
      store.keys
    end

    def all_data
      store
    end
  end
end
