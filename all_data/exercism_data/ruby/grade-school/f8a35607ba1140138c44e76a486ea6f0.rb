class School

  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    if db[grade]
      db[grade] << name
    else
      db[grade] = [name]
    end
  end

  def grade(grade)
    db.fetch(grade, [])
  end

  def sort
    Hash[sort_inside]
  end

  private

  def sort_inside
    db.each_pair { |k,v| db[k] = v.sort }.sort
  end
end
