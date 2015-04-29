class School

  attr_accessor :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    db[grade] ||= []
    db[grade] << name
  end

  def grade(grade)
    db[grade] || []
  end

  def sort
    db.sort.inject({}) do |store, item|
      store[item.first] = item.last.sort
      store
    end
  end

end
