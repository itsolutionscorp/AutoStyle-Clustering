require 'forwardable'
class Bst

  extend Forwardable
  def_delegator :entries, :each

  include Enumerable # not needed, but since I'm defining each...

  attr_reader :data, :left, :right

  def initialize(root_data)
    @data = root_data
    set_node :left; set_node :right
  end

  def insert(new_data)
    (send new_data <= data ? :left : :right).insert(new_data)
  end

  def entries
    [left.entries, data, right.entries].flatten
  end

  protected def set_node(position, node = Bst::Null.new(self, position))
    instance_variable_set :"@#{position}", node
  end

  class Null < Bst
    def initialize(parent, position)
      @parent = parent
      @position = position
    end

    def insert(new_data)
      @parent.set_node( @position, Bst.new(new_data) )
    end

    def entries; []; end

  end

end
