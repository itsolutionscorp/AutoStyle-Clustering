class Robot
  attr_reader :name

  @names = nil

  def initialize
    get_name
  end

  def reset
    get_name
  end

  private

  def get_name
    @name = self.class.request_name
  end

  class << self

    def request_name
      initialize_names unless @names
      @names[@name_index += 1]
    end

    def initialize_names
      @name_index = -1

      prefixes = ('A'..'Z')
        .to_a
        .permutation(2)
        .map {|two_letters| two_letters.join}
      suffixes = 1000
        .times
        .map {|number| "%03d"%number}
      @names = prefixes
        .product(suffixes)
        .map {|name_pair| name_pair.join}
      @names.shuffle!
    end

  end
end
