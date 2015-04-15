class Series

  def initialize(string)
    @string = string
  end

  def slices(size)
    raise ArgumentError if size > @string.length
    
    array = @string.split('').map(&:to_i)
    if size == 1
      array.map { |n| [n] }
    else
      store = []
        array.each_with_index do |letter, index|   
          if index + size <= array.length
          index
          array[index]
          store << array[index..index+size-1] 
          end
        end
      store
    end
  end

end
