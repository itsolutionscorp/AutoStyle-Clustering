class Proverb

  def to_s
    @objects = translate_objects
    put_objects_into_story
    format_proverb.join
  end

private

  def initialize(*args, qualifier: nil)
    @args = args
    @qualifier = qualifier
  end

  def repeating_line(small_object, large_object)
    "For want of a #{small_object} the #{large_object} was lost.\n"
  end

  def final_line(tiny_object)
    tiny_object = add_qualifiers(tiny_object)
    "And all for the want of a #{tiny_object}."
  end

  def put_objects_into_story
    @objects.count.times do |count|
      unless count == 0
        @objects[count] = repeating_line(@args[count - 1], @args[count])
      end
    end
    @objects[0] = final_line(@objects[0])
  end

  def format_proverb
    proverb = []
    last_line = @objects.delete(0)
    @objects.each do |k, v|
      proverb << v
    end
    proverb << last_line
  end

  def translate_objects
    hash = {}
    @args.count.times do |count|
      hash[count] = @args[count]
    end
    hash
  end

  def add_qualifiers(object)
    if @qualifier
      object = (@qualifier + " " + object).strip
    end
    object
  end
end
