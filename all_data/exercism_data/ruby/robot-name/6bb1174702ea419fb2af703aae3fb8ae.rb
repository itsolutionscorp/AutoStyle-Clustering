class Robot

  def initialize
    @name_cache ||= []
  end

  def name
    @name ||= get_name
  end

  def reset
    @name = nil
  end

  private

  def name_exists?(name)
    @name_cache.include? name
  end

  def cache_name(name)
    return nil if name_exists? name 
    @name_cache << name
  end

  def generate_name
    name = random_letters(2) + random_numbers(3)
    cache_name(name) ? name : generate_name
  end

  def get_name
    generate_name
  end

  def random_letters(count)
    (0...count).map { (65 + rand(26)).chr }.join
  end

  def random_numbers(count)
    (0...count).map { rand(9)}.join
  end
end
