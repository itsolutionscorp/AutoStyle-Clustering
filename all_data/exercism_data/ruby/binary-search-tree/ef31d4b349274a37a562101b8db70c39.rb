class Bst
  attr_accessor :data

  def initialize(data = nil)
    @data = data
  end

  def each
    left.each { |node| yield node } if left?
    yield data
    right.each { |node| yield node } if right?
  end

  def insert(new_data)
    if data.nil?
      self.data = new_data
    elsif new_data <= data
      left.insert(new_data)
    else
      right.insert(new_data)
    end
  end

  def left
    @left ||= self.class.new
  end

  def right
    @right ||= self.class.new
  end

  private

  def left?
    !@left.nil?
  end

  def right?
    !@right.nil?
  end
end
