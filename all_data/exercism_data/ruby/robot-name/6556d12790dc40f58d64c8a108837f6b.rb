class Robot
  def initialize(baptizer=Baptizer.new)
    @baptizer = baptizer
  end

  def name
    @name ||= new_name
  end

  def reset
    @name = nil
  end

  private

  def baptizer
    @baptizer
  end

  def new_name
    baptizer.generate_name
  end
end

class Baptizer

  @@name_pool = []

  def generate_name
    guarantee_unique_name { name_prefix + name_suffix }
  end

  private

  def guarantee_unique_name
    begin
      name = yield
    end while @@name_pool.include? name
    @@name_pool << name
    name
  end

  def name_prefix
    name_prefix_character + name_prefix_character
  end

  def name_prefix_character_pool
    ('A'..'Z').to_a
  end

  def name_prefix_character
    name_prefix_character_pool.shuffle.first
  end

  def name_suffix
    name_suffix_character + name_suffix_character + name_suffix_character
  end

  def name_suffix_character
    name_suffix_character_pool.shuffle.first
  end

  def name_suffix_character_pool
    ('1'..'9').to_a
  end
end
