class Hamming

  class << self
    attr_accessor :first, :second

    def compute *args
      @first, @second = self.letters_to_a args
      count_differences first, second
    end

    def count_differences first, second
      (0...index_min).inject(0) do |count,index|
        count + (bool_to_i(same_value?(index)))
      end
    end

    def same_value? index
      @first[index] == @second[index]
    end

    def bool_to_i bool
      bool ? 0 : 1
    end

    def index_min
      [first.size,second.size].min
    end

    def letters_to_a args
      args.map {|letters| letters.split('')}
    end

  end
end
