class Trinary

  def initialize values
    @values = values
  end

  def to_decimal
    return 0 if @values[ /[^012]/ ]

    @values.split('')
           .reverse_each
           .with_index
           .inject(0) do |sum,(n,i)|
      sum + n.to_i * 3**i
    end
  end

end
