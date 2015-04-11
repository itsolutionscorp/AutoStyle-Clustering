require 'forwardable'

class Bst
  extend Forwardable
  class Node
    attr_accessor :left, :right
    attr_reader :data
    def initialize(data, left_node=nil, right_node=nil)
      @data = data
      @left = left_node
      @right= right_node
    end

    def insert(new_node_data)
      if (new_node_data <= data && left.nil?)
        @left = Node.new(new_node_data)
      elsif (new_node_data <= data)
        left.insert(new_node_data)
      elsif (new_node_data > data && right.nil?)
        @right = Node.new(new_node_data)
      elsif (new_node_data > data)
        right.insert(new_node_data)
      end
    end

    def each(&block)
      left.each(&block) unless left.nil?
      yield(data)
      right.each(&block) unless right.nil?
    end
  end

  def_delegators :@root, :data, :left, :right, :insert, :each
  def initialize(root_data)
    @root = Node.new(root_data)  
  end
end
