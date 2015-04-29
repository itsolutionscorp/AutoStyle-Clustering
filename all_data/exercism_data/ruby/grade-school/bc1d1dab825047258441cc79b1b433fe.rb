class School
  attr_accessor :db

  def initialize
    @db = Hash.new {|h, k| h[k] = [] }
  end

  def add(name, grade)
    db[grade] << name
  end

  def grade grade
    db[grade]
  end

  def sort
    db.sort.reduce({}) do |red, e|
      red[e.first] = e.last.sort
      red
    end
  end
end
