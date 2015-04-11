require 'forwardable'
class Bst

  extend Forwardable
  def_delegator :entries, :each

  include Enumerable # not needed, but since I'm defining each...

  attr_reader :data, :left, :right

  def initialize(root_data)
    init_node(root_data)
  end

  def init_node(new_data)
    @data = new_data
    unless new_data.nil?
      @left = create_node(nil)
      @right = create_node(nil)
    end
  end

  def insert(new_data)
    if data.nil?
      init_node(new_data)
    elsif new_data <= data
      left.insert(new_data)
    else
      right.insert(new_data)
    end
  end

  def entries
    return [] if data.nil?
    [left.entries, data, right.entries].flatten
  end

  private def create_node(data)
    self.class.new(data)
  end
end
