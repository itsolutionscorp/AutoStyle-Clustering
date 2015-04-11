class DB
  def initialize
    @db = Hash.new {|h, k| h[k] = [] }
  end

  def add(name, grade)
    @db[grade] << name
  end

  def get_per_grade(grade)
    @db[grade]
  end

  def sort
    @db.sort.reduce({}) do |red, e|
      red[e.first] = e.last.sort
      red
    end
  end

  def raw
    @db
  end

end

class School
  def initialize
    @db = DB.new
  end

  def add(name, grade)
    @db.add name, grade
  end

  def grade grade
    @db.get_per_grade grade
  end

  def sort
    @db.sort
  end

  def db
    @db.raw
  end
end
