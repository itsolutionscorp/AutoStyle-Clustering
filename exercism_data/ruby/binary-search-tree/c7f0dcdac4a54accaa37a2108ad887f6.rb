class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
    @left = nil
    @right = nil
  end

  def insert(node)
    if node <= @data
      @left.nil? ? @left = Bst.new(node) : @left.insert(node)
    elsif node > @data
      @right.nil? ? @right = Bst.new(node) : @right.insert(node)
    else
      raise 'Value already exists in tree'
    end
  end

  def each
    @left.each unless @left.nil?
    @right.each unless @right.nil?
    data
  end

end
