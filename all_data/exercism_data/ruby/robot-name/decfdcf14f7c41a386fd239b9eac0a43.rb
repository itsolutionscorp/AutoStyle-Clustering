class Robot
    def name
        @name ||= Random.rand.to_s
    end

    def reset
        @name = nil
    end
end
