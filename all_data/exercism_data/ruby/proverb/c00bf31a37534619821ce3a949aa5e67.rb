class Proverb
  def initialize *inputs
    @options = inputs.find   {|x| x.kind_of? Hash   } || {}
    @losses  = inputs.select {|x| x.kind_of? String }
  end

  def to_s
    verses.inject(:+)
  end

  def steps
    @losses.each_cons(2)
  end

  def verses
    steps.map {|step| aggrevation(*step) }
         .push(culmination)
  end

  def aggrevation(cause, effect)
    "For want of a #{cause} the #{effect} was lost.\n"
  end

  def culmination
    "And all for the want of a #{qualifier}#{@losses.first}."
  end

  def qualifier
    if @options[:qualifier]
      @options[:qualifier] + ' '
    else
      ''
    end
  end
end
