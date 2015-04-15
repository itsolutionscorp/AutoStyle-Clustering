class Bst

  include Enumerable # not needed, but since I'm defining each...

  attr_reader :data, :left, :right

  def initialize(root_data)
    @data = root_data
    @left = nil
    @right = nil
  end

  def insert(new_data)
    if new_data <= @data
      if @left.nil?
        @left = create_node(new_data)
      else
        @left.insert(new_data)
      end
    else
      if @right.nil?
        @right = create_node(new_data)
      else
        @right.insert(new_data)
      end
    end
  end

  def each
    if block_given?
      entries.each do |entry|
        yield(entry)
      end
    else
      entries.each
    end
  end

  def entries
    left_entries = @left.nil? ? [] : @left.entries
    right_entries = @right.nil? ? [] : @right.entries
    [left_entries, data, right_entries].flatten
  end

  private def create_node(data)
    self.class.new(data)
  end
end
