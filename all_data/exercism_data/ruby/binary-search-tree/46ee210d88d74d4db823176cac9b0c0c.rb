class Bst
  attr_reader :data, :left, :right

  def initialize(data = nil)
    @data = data
  end

  def left
    @left ||= self.class.new
  end

  def right
    @right ||= self.class.new
  end

  def insert(data)
    if @data.nil?
      @data = data
    elsif @data < data
      right.insert(data)
    else
      left.insert(data)
    end
  end

  def each(&block)
    left.each(&block) unless left.data.nil?
    yield data
    right.each(&block) unless right.data.nil?
  end
end
