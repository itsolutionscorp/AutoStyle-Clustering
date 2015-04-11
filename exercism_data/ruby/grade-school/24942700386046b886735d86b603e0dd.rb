class School
  attr_accessor :db

  def initialize
    @db = {}
  end

  def add student, grade
    db[grade] ||= []
    db[grade].push student
  end

  def grade year_number
    db[year_number] || []
  end

  def sort
    sorted_hash = {}
    sorted_array = db.each_value do |value|
      value.sort!
    end.sort_by {|key, v| key }

    sorted_array.each do |ary|
      sorted_hash[ary[0]] = ary[1]
    end
    sorted_hash
  end
end
