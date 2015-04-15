class School

  attr_reader :db

  def initialize()
    @db = Hash.new{|hash, key| hash[key] = []}
  end

  def add(name, grade)
    @db[grade].push(name)
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    @db.sort.each_with_object({}) do |(grade, names), roster|
      roster[grade] = names.sort
    end
  end
end
