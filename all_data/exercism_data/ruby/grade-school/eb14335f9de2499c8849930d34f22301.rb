class School
  def initialize
    @grades = {}
  end
  def add(value, key)
    if @grades[key]
      @grades[key] << value
    else
      @grades[key] = [value]
    end
  end

  def grade(year)
    unless @grades[year]
      return []
    end
    @grades[year].sort
  end

  def to_hash
    sorted = {}
    @grades.sort_by { |year, name| year }.each do |arr|
      sorted[arr[0]] = arr[1].sort
    end
    sorted
  end

end
