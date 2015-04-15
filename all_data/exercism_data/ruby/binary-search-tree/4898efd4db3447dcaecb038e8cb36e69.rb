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
    position = new_data <= data ? :left : :right
    (send position).insert(new_data) or set_node(position, Bst.new(new_data))
  end

  def entries
    left.entries + [data] + right.entries
  end

  protected def set_node(position, node = Bst::NullNode)
    instance_variable_set :"@#{position}", node
  end

  module NullNode
    def self.insert(new_data); false; end
    def self.entries; []; end
  end

end
