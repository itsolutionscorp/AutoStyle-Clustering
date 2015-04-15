class School
  def initialize
    @to_hash = Hash.new([])
  end

  def to_hash
    sort
  end

  def add(name, grade)
    if @to_hash[grade].empty?
      @to_hash[grade] += [name]
    else
      @to_hash[grade] << name
    end
  end

  def grade(year)
    @to_hash[year].sort
  end

  private

  def sort
    Hash[sort_grades.zip(sort_names)]
  end

  def sort_names
    sort_grades.map do |name|
      @to_hash[name].sort
    end
  end

  def sort_grades
    @to_hash.keys.sort
  end
end
