class Teenager

    def new
        self
    end

    def hey(what)
        return "Fine. Be that way!" if is_silence(what)
        return "Woah, chill out!" if is_shout(what)
        return "Sure." if is_question(what)
        "Whatever."
    end

    private

    def is_question(what)
        what[-1,1] == '?'
    end

    def is_shout(what)
        what == what.upcase && what != what.downcase
    end

    def is_silence(what)
        what.strip == ''
    end

end

Bob = Teenager.new
