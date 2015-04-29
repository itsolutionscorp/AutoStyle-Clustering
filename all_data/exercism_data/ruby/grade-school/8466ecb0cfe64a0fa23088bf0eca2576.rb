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
    @db.sort.each_with_object({}) do |grade_names, roster|
      roster[grade_names[0]] = grade_names[1].sort
    end
  end
end
