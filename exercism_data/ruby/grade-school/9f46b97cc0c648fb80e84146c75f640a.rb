class School

  def initialize
    @record = Hash.new []
  end

  def to_hash
    @record.keys.sort.each_with_object({}) { |k,h| h[k]=@record[k] }
  end

  def add student, grade
    if @record[grade].empty?
      @record[grade] = []
    end
    @record[grade] << student
    @record[grade] = @record[grade].sort
  end

  def grade g
    return @record[g]
  end
end
