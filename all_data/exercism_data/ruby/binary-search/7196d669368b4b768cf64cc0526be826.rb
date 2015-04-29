class BinarySearch
  attr_reader :data

  def initialize(input_data)
    @data = validate(input_data)
  end

  def middle
    data.size / 2
  end

  def list
    data
  end

  def search_for(query)
    raise RuntimeError unless data.include?(query)
    result = data[middle]
    case 
    when result > query
      self.new([1..middle]).search_for(query)
    when result < query
      self.new([middle..middle]).search_for(query)
    end
  end

  private

  def validate(input_data)
    raise ArgumentError if input_data.sort != input_data
    input_data
  end
end
