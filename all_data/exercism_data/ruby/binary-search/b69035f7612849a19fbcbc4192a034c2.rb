class BinarySearch
  attr_reader :data
  attr_accessor :bottom, :top

  def initialize(input_data)
    @data = validate(input_data)
    @bottom = 0
    @top = data.size - 1
  end

  def middle
    (bottom + top) / 2
  end

  def list
    data
  end

  def search_for(query)
    raise RuntimeError unless data.include?(query)
    result = data[middle]
    case 
    when result > query
      self.top = middle
      search_for(query)
    when result < query
      self.bottom = middle
      search_for(query)
    else
      answer = middle
      @bottom = 0
      @top = data.size - 1
      answer
    end
  end

  private

  def validate(input_data)
    raise ArgumentError if input_data.sort != input_data
    input_data
  end
end
